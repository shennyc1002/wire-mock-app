package com.desk.otter.pdfprocessor.entity;


import java.util.Date;

public class FilePDF {

    private String blueprintId;
    private String currentProcessingPhase;
    private String fileLocation;
    private Date createTime;

    public String getBlueprintId() {
        return blueprintId;
    }

    public void setBlueprintId(String blueprintId) {
        this.blueprintId = blueprintId;
    }

    public String getCurrentProcessingPhase() {
        return currentProcessingPhase;
    }

    public void setCurrentProcessingPhase(String currentProcessingPhase) {
        this.currentProcessingPhase = currentProcessingPhase;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
