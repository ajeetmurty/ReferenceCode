import logging.config
import os
import platform
import configparser
from pymongo import MongoClient
from random import randint

logging.config.fileConfig('logging.conf')
logr = logging.getLogger('pylog')
config = configparser.ConfigParser()
config.read('config/mongolab.ini')
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
        do_connection()
        do_insertion()
    except Exception: 
        logr.exception('Exception')
    logr.info('stop')

def print_sys_info():
    logr.info('login|hostname|os|python : {0}|{1}|{2}|{3}.'.format(os.getlogin(), platform.node() , platform.system() + '-' + platform.release() , platform.python_version()))

def do_connection():
    logr.info('testing mongodb connection...')
    conn_string = MONGODB_URI.format(MONGODB_TOKEN01, MONGODB_TOKEN02, MONGODB_HOST, MONGODB_PORT, MONGODB_DATABASE)
    logr.info('connecting uri : ' + conn_string)
    client = MongoClient(conn_string)
    logr.info('mongodb server info: ' + str(client.server_info()))
    db = client[MONGODB_DATABASE]
    coll = db[MONGODB_COLLECTION]
    logr.info('document count: ' + str(coll.count()))
    client.close()

def do_insertion():
    logr.info('testing mongodb insert...')
    conn_string = MONGODB_URI.format(MONGODB_TOKEN01, MONGODB_TOKEN02, MONGODB_HOST, MONGODB_PORT, MONGODB_DATABASE)
    logr.info('connecting uri : ' + conn_string)
    client = MongoClient(conn_string)
    logr.info('mongodb server info: ' + str(client.server_info()))
    db = client[MONGODB_DATABASE]
    coll = db[MONGODB_COLLECTION]
    
    logr.info('document count: ' + str(coll.count()))
    dict_temp = {}
    dict_temp['pid'] = randint(0,100000)
    dict_temp['firstname'] = "xxx"
    dict_temp['lastname'] = "yyy"
    dict_temp['phone'] = "1-123-456-3459"
    obj_id = coll.insert(dict_temp)
    logr.info('insertion successful: ' + str(obj_id) + ' : ' + str(dict_temp))
    logr.info('document count: ' + str(coll.count()))
    
    client.close()

if __name__ == '__main__':
    main()
