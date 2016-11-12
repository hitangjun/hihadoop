package com.hitangjun.hihadoop.sample.userlog;

/**
 * @author JohnTang
 * @date 2016/11/10
 */
public class UserLog {
    private String date;
    private String uuid;
    private String url;
    private String referer;
    private String phone;
    private String clientType;
    private String appType;
    private String clientFlag;
    private String ua;
    private String version;
    private String channel;
    private String ip;
    private String province;
    private String city;
    private String model;
    private String resolution;
    private String system;
    private String queryStr;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getClientFlag() {
        return clientFlag;
    }

    public void setClientFlag(String clientFlag) {
        this.clientFlag = clientFlag;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getQueryStr() {
        return queryStr;
    }

    public void setQueryStr(String queryStr) {
        this.queryStr = queryStr;
    }

    public String toString(){

        StringBuilder sb = new StringBuilder();
        sb.append("date="+date + "|uuid=");
        sb.append(uuid + "|url=");
        sb.append(url + "|referer=");
        sb.append(referer + "|phone=");
        sb.append(phone + "|clientType=");
        sb.append(clientType + "|appType=");
        sb.append(appType + "|clientFlag=");
        sb.append(clientFlag + "|ua=");
        sb.append(ua + "|version=");
        sb.append(version + "|channel=");
        sb.append(channel + "|ip=");
        sb.append(ip + "|province=");
        sb.append(province + "|city=");
        sb.append(city + "|model=");
        sb.append(model + "|resolution=");
        sb.append(resolution + "|system=");
        sb.append(system + "|queryStr=");
//        sb.append(queryStr + "|");

        return sb.toString();
    }

    public UserLog(String ulstr){
        if(ulstr == null || ulstr.length() == 0){
            return;
        }
        String[] arr = ulstr.split("\\|");
        if(arr.length < 17)
            return;

        int i = 0;
        this.date = arr[i++];
        this.uuid = arr[i++];
        this.url = arr[i++];
        this.referer = arr[i++];
        this.phone = arr[i++];
        this.clientType = arr[i++];
        this.appType = arr[i++];
        this.clientFlag = arr[i++];
        this.ua  = arr[i++];
        this.version  = arr[i++];
        this.channel  = arr[i++];
        this.ip  = arr[i++];
        this.province  = arr[i++];
        this.city  = arr[i++];
        this.model  = arr[i++];
        this.resolution  = arr[i++];
        this.system  = arr[i++];
//        this.queryStr  = arr[i++];

    }

    public static void main(String args[]) {
        String line = "20161108000413|0EDF568D-A755-4BCB-84FD-859B2C3A0E4D|/myMerchant/myMerchantV24||13531437142|2|hz_aml|1||1.6.1|appstore|14.214.11.119|广东|佛山|iPhone|{1242, 2208}|iPhone OS 9.3.3|";
        System.out.println(line);
        UserLog ulog = new UserLog(line);
        System.out.println(ulog);
    }
}
