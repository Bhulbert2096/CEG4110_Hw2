package com.example.admin.myclock;

public interface Command {

    void execute();
    void undo();
    void redo();
}
