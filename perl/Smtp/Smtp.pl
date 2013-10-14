#!/usr/bin/perl

#imports
use strict;
use warnings 'FATAL' => 'all';
use Sys::Hostname;
use Cwd 'abs_path';
use Log::Log4perl;
use Net::SMTP;    #need to install external package/module

#vars
my $logConfig = 'log4perl.conf';
Log::Log4perl::init($logConfig);
my $logr            = Log::Log4perl->get_logger();
my $userName        = getlogin || 'undef-user';
my $hostName        = hostname || 'undef-hostname';
my $smtpServerHost  = '127.0.0.1';
my $smtpServerPort  = '25';
my $smtpFromAddress = 'from@smtp.org';
my @smtpToAddress   = ( 'to01@smtp.org', 'to02@smtp.org' );

#methods
sub sendEmail() {
	$logr->info('preparing email.');

	my $emailSubject  = "test email from $hostName";
	my $emailContent  = "test email details.\n\tsent at: " . localtime() . "\n\tsent from: $hostName \n\tsent by: $userName";
	my $smtpToAddress = join( ',', @smtpToAddress );

	$logr->info( 'smtp server: ' . $smtpServerHost . ':' . $smtpServerPort );
	$logr->info( 'from address: ' . $smtpFromAddress );
	$logr->info( 'to addresses: ' . $smtpToAddress );
	$logr->info( 'email subject: ' . $emailSubject );
	$logr->info( 'email content: ' . $emailContent );

	if ( scalar(@smtpToAddress) != 0 ) {
		my $smtpObj = Net::SMTP->new( $smtpServerHost, Timeout => 10, Port => $smtpServerPort, Debug => 1 );
		if ($smtpObj) {
			$logr->info('connection to SMTP server established.');
			$logr->info('sending email...');
			$smtpObj->mail($smtpFromAddress);
			$smtpObj->to(@smtpToAddress);
			$smtpObj->data();
			$smtpObj->datasend("To: $smtpToAddress\n");
			$smtpObj->datasend("Subject: $emailSubject\n");
			$smtpObj->datasend("START\n");
			$smtpObj->datasend("$emailContent\n");
			$smtpObj->datasend("END\n");
			$smtpObj->dataend();
			$smtpObj->quit();
			$logr->info('email sent');
		} else {
			die $logr->error("unable to establish connection: $smtpServerHost:$smtpServerPort");
		}
	} else {
		die $logr->error("recipient email addresses invalid/missing.");
	}
}

#main
$logr->info('start');
eval {
	$logr->info('this is a smtp tool.');
	$logr->info("executed by: $userName\@$hostName");
	$logr->info( 'execution path: ' . abs_path($0) );
	sendEmail();
};
if ($@) {
	$logr->error( 'error: ', $@ );
}
$logr->info('stop');
