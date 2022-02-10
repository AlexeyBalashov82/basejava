package com.entrancetest;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

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
        int i = searchIndex(uuid);
        if (i < 0) {
            System.out.println("Not found resume with uuid: " + uuid);
        }
        return (i < 0) ? null : storage[i];
    }

    void delete(String uuid) {
        int i = searchIndex(uuid);
        if (i < 0) {
            System.out.println("Not found resume with uuid: " + uuid);
        } else {
            storageSetRange(Arrays.copyOfRange(storage, 0, i), 0);
            storageSetRange(Arrays.copyOfRange(storage, i + 1, resumeCount), i);
            resumeCount--;
            storage[resumeCount] = null;
        }
    }

    private int searchIndex(String uuid) {
        boolean isResumeInStorage = false;
        int i = 0;
        while (i < resumeCount) {
            isResumeInStorage = storage[i].toString().equals(uuid);
            if (isResumeInStorage) {
                break;
            }
            i++;
        }
        return (isResumeInStorage) ? i : -1;
    }

    private void storageSetRange(Resume[] sourceStorage, int startIndex) {
        for (int i = 0; i <= sourceStorage.length - 1; i++) {
            storage[startIndex + i] = sourceStorage[i];
        }
    }

    Resume[] getAll() {
        return (resumeCount > 0) ? Arrays.copyOfRange(storage, 0, resumeCount) : new Resume[0];
    }

    int size() {
        return resumeCount;
    }
}