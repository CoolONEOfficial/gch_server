package ru.ruykarpuni.testservlet.dtos;

import java.io.Reader;
import java.util.Map;

public interface IDriver {
    boolean write(Map<String, String> map);

    Reader read(String uri);

    boolean delete(String uri);

    void setChannel(String channel);

    void setKey(String key);
}
