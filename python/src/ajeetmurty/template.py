import logging.config
import os
import platform

logging.config.fileConfig('../../resources/logging.conf')
logr = logging.getLogger('pyLog')

def main():
    try:
        logr.info('start')
        print_sys_info()
        do_something()
        return 0
    except Exception: 
        logr.exception('exception!')
        return 1
    finally:
        logr.info('stop')

def print_sys_info():
    logr.info('login|hostname|os|python: ' + os.getlogin() + '|' + platform.node() + '|' + platform.system() + '-' + platform.release() + '|' + platform.python_version())

def do_something():
    logr.info('doing something')

if __name__ == '__main__':
    main()
