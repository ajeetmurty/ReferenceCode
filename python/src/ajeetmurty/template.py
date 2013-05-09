import logging.config
import os
import sys

logging.config.fileConfig('../../resources/logging.conf')
logr = logging.getLogger('pyLog')

def main():
    logr.info('start')
    print_sys_info()
    logr.info('stop')

def print_sys_info():
    logr.info('login: '+ os.getlogin())
    logr.info('os: '+ sys.platform)

if __name__ == '__main__':
    main()