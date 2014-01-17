#!/usr/bin/perl

#imports
use strict;
use warnings 'FATAL' => 'all';
use Sys::Hostname;
use Cwd 'abs_path';
use Log::Log4perl;                 #need to install external package/module
use Digest::SHA qw(sha512_hex);    #need to install external package/module

#vars
my $logConfig = 'log4perl.conf';
Log::Log4perl::init($logConfig);
my $logr      = Log::Log4perl->get_logger();
my $userName  = getlogin || 'undef-user';
my $hostName  = hostname || 'undef-hostname';
my $inputText = 'oXSG_BzpnkK huWx!R5iUDqZ&c2^V';

#methods
sub generateChecksum() {
	$logr->info("input text: $inputText");
	my $digestTxtObj = Digest::SHA->new(512);
	$digestTxtObj->add($inputText);
	$logr->info( "sha512 obj-oriented checksum: " . $digestTxtObj->hexdigest );
	$logr->info( "sha512 checksum: " . sha512_hex($inputText) );
}

#main
$logr->info('start');
eval {
	$logr->info('this is a checksum generation tool.');
	$logr->info("executed by: $userName\@$hostName");
	$logr->info( 'execution path: ' . abs_path($0) );
	generateChecksum();
};
if ($@) {
	$logr->error( 'error: ', $@ );
}
$logr->info('stop');
