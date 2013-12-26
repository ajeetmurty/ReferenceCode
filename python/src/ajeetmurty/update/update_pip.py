import logging.config
import os
import platform
import pip
from subprocess import call

logging.config.fileConfig('logging.conf')
logr = logging.getLogger('pylog')

def main():
    logr.info('start')
    try:
        print_sys_info()
        do_pip_update()
    except Exception: 
        logr.exception('Exception')
    logr.info('stop')

def print_sys_info():
    logr.info('login|hostname|os|python : {0}|{1}|{2}|{3}.'.format(os.getlogin(), platform.node() , platform.system() + '-' + platform.release() , platform.python_version()))

def do_pip_update():
    for dist in pip.get_installed_distributions():
        call("pip install --upgrade " + dist.project_name, shell=True)
        
if __name__ == '__main__':
    main()
