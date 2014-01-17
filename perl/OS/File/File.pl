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
my $logr     = Log::Log4perl->get_logger();
my $userName = getlogin || 'undef-user';
my $hostName = hostname || 'undef-hostname';
my $testFile = 'test.log';

#methods
sub openFile() {
	$logr->info("input file: $testFile");
	my $count = 0;
	open my $filePtr, $testFile or die "Could not open $testFile: $!";
	while ( my $line = <$filePtr> ) {
		print "\t" . $line;
		$count++;
	}
	print "\n";
	$logr->info( 'total lines in file: ', $count );
}

#main
$logr->info('start');
eval {
	$logr->info('this is a file-read tool.');
	$logr->info("executed by: $userName\@$hostName");
	$logr->info( 'execution path: ' . abs_path($0) );
	openFile();
};
if ($@) {
	$logr->error( 'error: ', $@ );
}
$logr->info('stop');
