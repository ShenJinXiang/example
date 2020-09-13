package com.shenjinxiang.interaction.core;

import com.shenjinxiang.interaction.entity.ArConfig;
import com.shenjinxiang.interaction.entity.RunStatus;

public class Config {

    public static ArConfig AR_CONFIG;

    public static Sender SENDER = new Sender();

    public static final String WAVE_DATA_PREFIX = "DATA";

    public static RunStatus RUNSTATUS = RunStatus.NONE;
}
