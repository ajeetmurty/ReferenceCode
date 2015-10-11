import logging.config
import os
import platform
import csv
import re

logging.config.fileConfig('logging.conf')
logr = logging.getLogger('pylog')

def main():
    logr.info('start')
    try:
        print_sys_info()
        do_io()
    except Exception: 
        logr.exception('Exception')
    logr.info('stop')

def print_sys_info():
    logr.info('login|hostname|os|python : {0}|{1}|{2}|{3}.'.format(os.getlogin(), platform.node() , platform.system() + '-' + platform.release() , platform.python_version()))

def do_io():
    csvinput = open('io/input.csv', 'r')
    csvreader = csv.reader(csvinput, delimiter=',', quotechar='"')
    csvoutput = open('io/output.csv', 'w')
    csvwriter = csv.writer(csvoutput, delimiter=',', quotechar='"', quoting=csv.QUOTE_ALL, lineterminator='\n')
    rx = re.compile('([,ms\s])')
    
    for row in csvreader:
        status = row[0]
        timestp = row[1]
        if(str(row[0]) != 'up'):
            response = 30000
        else:     
            response = rx.sub("", row[2])
        error = row[3]
        location = row[4]
        temp_output = [status, timestp, response, error, location]
        print("parsed -->" + str(temp_output))
        csvwriter.writerow(temp_output)
    
    csvinput.close()
    csvoutput.close()

if __name__ == '__main__':
    main()
