package com.dbx;

/*
Why notifyAll()?
all threads waiting for read access are granted read access at once - not one by one.
Inside the ReadWriteLock there are threads waiting for read access, and threads waiting for write access.
If a thread awakened by notify() was a read access thread, it would be put back to waiting because there are threads waiting for write access. However, none of the threads awaiting write access are awakened, so nothing more happens. No threads gain neither read nor write access. By calling noftifyAll() all waiting threads are awakened and check if they can get the desired access.

Starvation:
Consider the situation more write or more read, we need to prioritise the read/write to avoid starvation.
To do this, we can add additional count call writeRequests/readRequest. If read/write more or less the same,
no need prioritise
Simple Version w/o Reentrant
*/
public class ReadWriteLock1 {
    private int readers = 0;
    private int writers = 0;
    private int writeRequests = 0;

    public synchronized void lockRead() throws InterruptedException {
        while (writers > 0 || writeRequests > 0) {
            wait();
        }
        readers++;
    }

    public synchronized void unlockRead() {
        readers--;
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        writeRequests++;
        while (readers > 0 || writers > 0) {
            wait();
        }
        writeRequests--;
        writers++;
    }

    public synchronized void unlockWrite() throws InterruptedException {
        writers--;
        notifyAll();
    }
}
