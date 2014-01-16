import logging.config
import os
import platform
from pymongo import MongoClient
from datetime import datetime

logging.config.fileConfig('logging.conf')
logr = logging.getLogger('pylog')
mongo_ip = '192.168.0.104'
mongo_port = 27017
mongo_db = 'mongotest'
mongo_collection = 'mongotesttable'

def main():
    logr.info('start')
    try:
        print_sys_info()
        do_mongo()
    except Exception: 
        logr.exception('Exception')
    logr.info('stop')

def print_sys_info():
    logr.info('login|hostname|os|python : {0}|{1}|{2}|{3}.'.format(os.getlogin(), platform.node() , platform.system() + '-' + platform.release() , platform.python_version()))

def do_mongo():
    out_dict = {}
    out_dict['test_string'] = 'test01 value'
    out_dict['test_int'] = 123456789
    out_dict['test_string_int'] = int('123456789')
    out_dict['test_float'] = 4.123456789
    out_dict['test_string_float'] = float('4.123456789')
    out_dict['test_date'] = datetime.utcnow()
    out_dict['test_string_date'] = datetime.strptime('Mon Jan 13 16:35:17 2014', '%a %b %d %H:%M:%S %Y')
    commit_to_db(out_dict)

def commit_to_db(input_dict):
    logr.info('committing this data: ' + str(input_dict))
    client = MongoClient(mongo_ip, mongo_port)
    logr.info('mongodb server info: ' + str(client.server_info()))
    db = client[mongo_db]
    coll = db[mongo_collection]
    obj_id = coll.insert(input_dict)
    logr.info('commit successful: ' + str(obj_id))
    client.close()

    
if __name__ == '__main__':
    main()
