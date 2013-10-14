#!/usr/bin/perl

#imports
use strict;
use warnings 'FATAL' => 'all';
use Sys::Hostname;
use Cwd 'abs_path';
use Log::Log4perl;    #need to install external package/module
use Net::Ping;        #need to install external package/module

#vars
my $logConfig = 'log4perl.conf';
Log::Log4perl::init($logConfig);
my $logr         = Log::Log4perl->get_logger();
my $userName     = getlogin || 'undef-user';
my $hostName     = hostname || 'undef-hostname';
my $pingHost     = 'localhost';
my $pingTimeout  = 15.5;
my $pingProtocol = 'icmp';

#methods
sub pingHost() {
	$logr->info("pinging host: $pingHost");
	my $pingObj = Net::Ping->new($pingProtocol);
	my ( $pingSuccessFlag, $pingElapsedTime, $pingResolvedIp ) = $pingObj->ping( $pingHost, $pingTimeout );

	if ($pingSuccessFlag) {
		$logr->info("host pinged successfully.");
		$logr->info("ping info: flag | duration | ip : $pingSuccessFlag | $pingElapsedTime | $pingResolvedIp");
		$pingObj->close();
	} else {
		$logr->info("failed to connect to: $pingHost");
	}
}

#main
$logr->info('start');
eval {
	$logr->info('this is a ping tool.');
	$logr->info("executed by: $userName\@$hostName");
	$logr->info( 'execution path: ' . abs_path($0) );
	pingHost();
};
if ($@) {
	$logr->error( 'error: ', $@ );
}
$logr->info('stop');
