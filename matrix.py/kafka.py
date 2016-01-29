#!/usr/bin/env python
import threading, logging, time, collections

from kafka.client import KafkaClient
from kafka.consumer import SimpleConsumer
from kafka.producer import SimpleProducer

msg_size = 512

class check_size:
    def __init__(self, x):
        self.x = x
    def get(self):
        return self.x
    def put(self,value):
        self.x=value


Producer(threading.Thread):
daemon = True
texto=raw_input('Enter your input:')
big_msg = texto * msg_size
check_size=len(texto) * msg_size
def run(self):
    client = KafkaClient("localhost:9092")
    producer = SimpleProducer(client)
    self.sent = 0
    i=1
    while i<10:
        producer.send_messages('my-topic', self.big_msg)
        self.sent += 1
        i +=1


class Consumer(threading.Thread):
    daemon = True

    def run(self):
        client = KafkaClient("localhost:9092")
        consumer = SimpleConsumer(client, "test-group", "my-topic",
                                  max_buffer_size = None,
                                  )
        self.valid = 0
        self.invalid = 0

        for message in consumer:
            if len(message.message.value) == 1:
                self.valid += 1
                print(message.message.value,"valid",check_size)
            else:
                self.invalid += 1
                print(message.message.value,"invalid",check_size)

                def main():
    threads = [
        Producer(),
        Consumer()
    ]

    for t in threads:
        t.start()

    time.sleep(10)
    print 'Messages sent: %d' % threads[0].sent
    print 'Messages recvd: %d' % threads[1].valid
    print 'Messages invalid: %d' % threads[1].invalid

if __name__ == "__main__":
    logging.basicConfig(
        format='%(asctime)s.%(msecs)s:%(name)s:%(thread)d:%(levelname)s:%(process)d:%(message)s',
        level=logging.WARNING
    )
    main()
