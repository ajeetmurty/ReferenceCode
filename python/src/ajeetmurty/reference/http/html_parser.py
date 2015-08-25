import logging.config
import os
import platform
from datetime import datetime
from requests import Request, Session
from bs4 import BeautifulSoup

logging.config.fileConfig('logging.conf')
logr = logging.getLogger('pylog')
google_url = 'https://duckduckgo.com/'

def main():
    logr.info('start')
    try:
        print_sys_info()
        parsing_output_dict = do_parsing()
        logr.info('parsing output: ' + str(parsing_output_dict))
    except Exception: 
        logr.exception('Exception')
    logr.info('stop')

def print_sys_info():
    logr.info('login|hostname|os|python : {0}|{1}|{2}|{3}.'.format(os.getlogin(), platform.node() , platform.system() + '-' + platform.release() , platform.python_version()))

def do_parsing():
    logr.info('get info from: ' + google_url)
    session = Session()
    prepped = Request('GET', google_url).prepare()
    response = session.send(prepped, stream=True, verify=False, timeout=10)
    content = response.raw.read().decode()

    if response:
        if(response.status_code == 200):
            logr.info('response: ' + content.replace('\r\n', ''))
            soup = BeautifulSoup(content)
            output_dict = {}
            output_dict['url'] = google_url
            output_dict['request_timestamp'] = datetime.utcnow()
            output_dict['title'] = soup.title
            return output_dict
        else:
            raise Exception('fail http response code: ' + str(response.status_code))
    else:
        raise Exception('null response object')        
    
if __name__ == '__main__':
    main()
