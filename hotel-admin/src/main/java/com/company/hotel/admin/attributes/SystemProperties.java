package com.company.hotel.admin.attributes;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class SystemProperties {

    @Value("${os.name}")
    private String osName;

    /**
     * 当前用户主目录
     */
    @Value("${user.home}")
    private String userHomeDirectory;
    @Override
    public String toString() {
        return "SystemPropertiesUser{" +
                "operatingSystemName='" + osName + '\'' +
                ", userHomeDirectory='" + userHomeDirectory + '\'' +
                '}';
    }
}
