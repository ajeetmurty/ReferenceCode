#!/usr/bin/perl
package Local::Ajeetmurty::Reference::Perl;

#imports
use strict;
use warnings 'FATAL' => 'all';
use Sys::Hostname;
use Cwd 'abs_path';
use Log::Log4perl;

#vars
my $log_conf = "log4perl.conf";
Log::Log4perl::init($log_conf);
my $logp     = Log::Log4perl->get_logger();
my $userName = $ENV{'LOGNAME'} || 'unknown-user';
my $hostName = hostname || 'unknown-hostname';

#methods
sub doTemplate {
	$logp->info('doing template');
	$logp->debug('doing log debug');
	$logp->error('doing log error');
}

#main
$logp->info('start');
eval {
	$logp->info('this is template.');
	$logp->info("executed by: $userName\@$hostName");
	$logp->info( 'execution path: ' . abs_path($0) );
	doTemplate();
};
if ($@) {
	$logp->error( 'error: ', $@ );
}
$logp->info('stop');
