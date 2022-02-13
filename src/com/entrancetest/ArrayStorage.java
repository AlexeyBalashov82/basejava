package com.entrancetest;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int resumeCount = 0;

    void clear() {
        for (int i = 0; i < resumeCount; i++) {
            storage[i] = null;
        }
        resumeCount = 0;
    }

    void save(Resume r) {
        if (searchIndex(r.toString()) < 0) {
            storage[resumeCount] = r;
            resumeCount++;
        } else {
            System.out.println("Not unique uuid: " + r);
        }
    }

    Resume get(String uuid) {
        int index = searchIndex(uuid);
        if (index < 0) {
            System.out.println("Not found resume with uuid: " + uuid);
        }
        return (index < 0) ? null : storage[index];
    }

    void delete(String uuid) {
        int index = searchIndex(uuid);
        if (index < 0) {
            System.out.println("Not found resume with uuid: " + uuid);
        } else {
            for (int j = index; j < resumeCount - 1; j++) {
                storage[j] = storage[j + 1];
            }
            resumeCount--;
            storage[resumeCount] = null;
        }
    }

    private int searchIndex(String uuid) {
        for (int i = 0; i < resumeCount; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, resumeCount);
    }

    int size() {
        return resumeCount;
    }
}