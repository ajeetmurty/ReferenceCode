#!/usr/bin/perl

#imports
use strict;
use warnings 'FATAL' => 'all';
use Sys::Hostname;
use Cwd 'abs_path';
use Log::Log4perl;    #need to install external package/module
use Net::NTP;         #need to install external package/module

#vars
my $logConfig = 'log4perl.conf';
Log::Log4perl::init($logConfig);
my $logr     = Log::Log4perl->get_logger();
my $userName = getlogin || 'undef-user';
my $hostName = hostname || 'undef-hostname';
my $ntpHost  = 'pool.ntp.org';
my $ntpPort  = 123;

#methods
sub ntpRequest() {
	$logr->info("ntp host: $ntpHost");
	my %responseArray = get_ntp_response( $ntpHost, $ntpPort );

	my $responseOutput = 'ntp response contents: ';
	while ( ( my $key, my $val ) = each(%responseArray) ) {
		$responseOutput = $responseOutput . "\n\t $key : $val";
	}
	$logr->info( $responseOutput );
}

#main
$logr->info('start');
eval {
	$logr->info('this is a ntp tool.');
	$logr->info("executed by: $userName\@$hostName");
	$logr->info( 'execution path: ' . abs_path($0) );
	ntpRequest();
};
if ($@) {
	$logr->error( 'error: ', $@ );
}
$logr->info('stop');
