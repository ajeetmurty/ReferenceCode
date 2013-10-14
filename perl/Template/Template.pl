#!/usr/bin/perl
package Local::Ajeetmurty::Reference::Perl;

#imports
use strict;
use warnings 'FATAL' => 'all';
use Sys::Hostname;
use Cwd 'abs_path';
use Log::Log4perl;

#vars
my $logConfig = 'log4perl.conf';
Log::Log4perl::init($logConfig);
my $logr     = Log::Log4perl->get_logger();
my $userName = getlogin || 'undef-user';
my $hostName = hostname || 'undef-hostname';

#methods
sub doTemplate {
	$logr->info('doing template');
	$logr->debug('doing log debug');
	$logr->error('doing log error');
}

#main
$logr->info('start');
eval {
	$logr->info('this is template.');
	$logr->info("executed by: $userName\@$hostName");
	$logr->info( 'execution path: ' . abs_path($0) );
	doTemplate();
};
if ($@) {
	$logr->error( 'error: ', $@ );
}
$logr->info('stop');
