#!/usr/bin/perl

#imports
use strict;
use warnings 'FATAL' => 'all';
use Sys::Hostname;
use Cwd 'abs_path';
use Log::Log4perl;    #need to install external package/module
use IPC::Run3;        #need to install external package/module

#vars
my $logConfig = 'log4perl.conf';
Log::Log4perl::init($logConfig);
my $logr      = Log::Log4perl->get_logger();
my $userName  = getlogin || 'undef-user';
my $hostName  = hostname || 'undef-hostname';
my $iperf_cmd = '/usr/bin/iperf -c 192.168.0.102 -P 1 -i 1 -p 5001 -f k -t 10';
#my $iperf_cmd = 'C:\iperf.exe -c 192.168.0.102 -P 1 -i 1 -p 5001 -f k -T 1 -t 5';

#methods
sub cmdExecute() {
	$logr->info("command: $iperf_cmd");
	my ( $in, $out, $err );
	run3( $iperf_cmd, \$in, \$out, \$err );
	my $returnVal = ( $? >> 8 );
	$logr->info("command return value: $returnVal");

	my $cmdOutput = 'command output: ';
	if ($err) {
		$cmdOutput = $cmdOutput . "\n[STDERR]\n" . $err;
	}
	if ($out) {
		$cmdOutput = $cmdOutput . "\n[STDOUT]\n" . $out;
	}
	$logr->info( 'command output: ' . $cmdOutput );
}

#main
$logr->info('start');
eval {
	$logr->info('this is a command-execute tool.');
	$logr->info("executed by: $userName\@$hostName");
	$logr->info( 'execution path: ' . abs_path($0) );
	cmdExecute();
};
if ($@) {
	$logr->error( 'error: ', $@ );
}
$logr->info('stop');
