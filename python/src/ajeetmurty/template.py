import logging.config
import os
import platform

logging.config.fileConfig('logging.conf')
logr = logging.getLogger('pylog')

def main():
    logr.info('start')
    try:
        print_sys_info()
        do_something()
    except Exception: 
        logr.exception('Exception')
    logr.info('stop')

def print_sys_info():
    logr.info('login|hostname|os|python : {0}|{1}|{2}|{3}.'.format(os.getlogin(), platform.node() , platform.system() + '-' + platform.release() , platform.python_version()))

def do_something():
    logr.info('class name: ' + __name__)
    
if __name__ == '__main__':
    main()
