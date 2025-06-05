package com.self_discovery.self_discovery.selfdiscovery.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class  ErrorDetails {
	@JsonIgnore
    private LocalDateTime timestamp;
    private String error;
    private String path;

    public ErrorDetails(LocalDateTime timestamp, String error, String path) {
        this.timestamp = timestamp;
        this.error = error;
        this.path = path;
    }
}