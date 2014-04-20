import os
import platform
import logging.config
import configparser
from pymongo import MongoClient

logging.config.fileConfig('logging.conf')
logr = logging.getLogger('pylog')
config = configparser.ConfigParser()
config.read('config/mongodb_mongolab-test.ini')
MONGODB_URI = config.get('connection_params', 'uri')
MONGODB_HOST = config.get('connection_params', 'host')
MONGODB_PORT = config.get('connection_params', 'port')
MONGODB_DATABASE = config.get('connection_params', 'database')
MONGODB_COLLECTION = config.get('connection_params', 'collection')
MONGODB_TOKEN01 = config.get('connection_params', 'token01')
MONGODB_TOKEN02 = config.get('connection_params', 'token02')

def main():
    logr.info('start')
    try:
        print_sys_info()
        test_mongo_lab_conn()
    except Exception: 
        logr.exception('Exception')
    logr.info('stop')

def print_sys_info():
    logr.info('login|hostname|os|python : {0}|{1}|{2}|{3}.'.format(os.getlogin(), platform.node() , platform.system() + '-' + platform.release() , platform.python_version()))

def test_mongo_lab_conn():
    conn_string = MONGODB_URI.format(MONGODB_TOKEN01, MONGODB_TOKEN02, MONGODB_HOST, MONGODB_PORT, MONGODB_DATABASE)
    logr.info('params connecting string : ' + conn_string)
    client = MongoClient(conn_string)
    logr.info('db server info: ' + str(client.server_info()))
    db = client[MONGODB_DATABASE]
    coll = db[MONGODB_COLLECTION]
    logr.info('document count: ' + str(coll.count()))
    logr.info('document dump: ' + str(list(coll.find())))
    client.close()


if __name__ == '__main__':
    main()
