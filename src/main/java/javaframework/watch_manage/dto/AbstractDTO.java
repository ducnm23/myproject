package javaframework.watch_manage.dto;

import javaframework.watch_manage.paging.PageResult;
import lombok.Data;

import java.util.Date;

@Data
public class AbstractDTO<T> extends PageResult<T> {
    private Long id;
    private boolean status;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;

    // alert
    private String alert;
    private String message;

    // Delete
    private Long[] ids;

    // Keep value search when searching
    private String search;
    // using to mark menu
    private String active;
}
