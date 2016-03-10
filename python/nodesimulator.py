from requests import post
from json import dumps
from random import gauss
from time import sleep
from multiprocessing import Process

url = "http://localhost:8080/nodes/webresources/datapoint/"
api_key = "ebca45c081b5c7f6326c7b5624ebea97"


class Node:
    def __init__(self, owner, name, api_key):
        self.owner = owner
        self.name = name
        self.api_key = api_key

    def report_value(self, value):
        headers = {"APIKey": self.api_key, "Owner": self.owner, "Content-Type": "application/json"}
        data = {"data": value, "datapointPK": {"owner": self.owner, "node": self.name}}
        post(url=url, data=dumps(data), headers=headers)


class Reporter:
    def __init__(self, node):
        self.node = node

    def start_normal_reporter(self, mean, deviation, interval):
        while True:
            value = gauss(mean, deviation)
            self.node.report_value(int(value))
            sleep(interval)


if __name__ == "__main__":
    node_1 = Node("admin", "LivingRoom", api_key)
    node_2 = Node("admin", "MasterBedRoom", api_key)
    node_3 = Node("admin", "KidsRoom", api_key)
    reporter_1 = Reporter(node_1)
    reporter_2 = Reporter(node_2)
    reporter_3 = Reporter(node_3)
    Process(target=reporter_1.start_normal_reporter, args=(18, 3, 10,)).start()
    Process(target=reporter_2.start_normal_reporter, args=(23, 1, 7,)).start()
    Process(target=reporter_3.start_normal_reporter, args=(20, 1, 5,)).start()
