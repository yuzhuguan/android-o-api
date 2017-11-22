package cn.codef1.library.markdownview.core;

import java.util.regex.Matcher;

/**
 * Created by CoderF1 on 2017/11/9.
 */

public interface Replacement {
    String replacement(Matcher m);
}