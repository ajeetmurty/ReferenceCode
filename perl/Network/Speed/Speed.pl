#!/usr/bin/perl

#imports
use strict;
use warnings 'FATAL' => 'all';
use Sys::Hostname;
use Cwd 'abs_path';
use Log::Log4perl;    #need to install external package/module
use LWP::Simple;
use Time::HiRes qw(time);
use File::stat;

#vars
my $logConfig = 'log4perl.conf';
Log::Log4perl::init($logConfig);
my $logr            = Log::Log4perl->get_logger();
my $userName        = getlogin || 'undef-user';
my $hostName        = hostname || 'undef-hostname';
my $downloadFileUrl = 'http://64.18.29.194/CDP/HHS-SSP-CA-B7.crl';
my $downLoadfile    = 'temp/downloaded.file';

#methods
sub measureSpeed() {
	$logr->info("file to download: $downloadFileUrl");

	my $timestamp         = localtime;
	my $download_start    = time;
	my $status            = getstore( $downloadFileUrl, $downLoadfile );
	my $download_duration = time - $download_start;
	my $filesize          = ( stat($downLoadfile)->size ) / ( 1024 * 1024 );
	my $speed             = sprintf( "%.4f", ( $filesize / $download_duration ) );

	$logr->info( 'downlad status: ' . $status );
	$logr->info( 'downlad time (secs): ' . $download_duration );
	$logr->info( 'file size (MB): ' . $filesize );
	$logr->info( 'speed (MB/sec): ' . $speed );
}

#main
$logr->info('start');
eval {
	$logr->info('this is a download-speed-measuring tool.');
	$logr->info("executed by: $userName\@$hostName");
	$logr->info( 'execution path: ' . abs_path($0) );
	measureSpeed();
};
if ($@) {
	$logr->error( 'error: ', $@ );
}
$logr->info('stop');
