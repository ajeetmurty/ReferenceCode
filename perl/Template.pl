#!/usr/bin/perl
package Local::Ajeetmurty::Reference::Perl;

use strict;
use warnings 'FATAL' => 'all';
use Log::Log4perl;

my $LOG_CONF_FILE = "log4perl.conf";
Log::Log4perl::init($LOG_CONF_FILE);
my $logp = Log::Log4perl->get_logger();

sub do_something {
	$logp->info('doing info');
	$logp->debug('doing debug');
	$logp->error('doing error');
}

#main
$logp->info('start');
eval { do_something(); };
if ($@) {
	$logp->error( 'error: ', $@ );
}
$logp->info('stop');
