// Copyright (c) 2017, Baidu.com, Inc. All Rights Reserved

// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package com.baidu.palo.broker.bos;


import com.baidu.palo.thrift.TBrokerOperationStatus;
import com.baidu.palo.thrift.TBrokerOperationStatusCode;
import com.baidu.palo.common.LoggerMessageFormat;

public class BrokerException extends RuntimeException {
    private static final long serialVersionUID = 6745484176667787474L;
    public final TBrokerOperationStatusCode errorCode;
    
    public BrokerException(TBrokerOperationStatusCode errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    /**
     * Construct a <code>BrokerException</code> with the specified detail message.
     *
     * The message can be parameterized using <code>{}</code> as place holders for the given
     * arguments
     *
     * @param msg the detail message
     * @param args the arguments for the message
     */
    public BrokerException(TBrokerOperationStatusCode errorCode, String msg, Object... args) {
        super(LoggerMessageFormat.format(msg, args));
        this.errorCode = errorCode;
    }

    /**
     * Construct a <code>BrokerException</code> with the specified detail message
     * and nested exception.
     *
     * The message can be parameterized using <code>{}</code> as place holders for the given
     * arguments
     *
     * @param msg   the detail message
     * @param cause the nested exception
     * @param args  the arguments for the message
     */
    public BrokerException(TBrokerOperationStatusCode errorCode, Throwable cause, String msg, Object... args) {
        super(LoggerMessageFormat.format(msg, args), cause);
        this.errorCode = errorCode;
    }
    
    public TBrokerOperationStatus generateFailedOperationStatus() {
        TBrokerOperationStatus errorStatus = new TBrokerOperationStatus(errorCode);
        errorStatus.setMessage(super.getMessage());
        return errorStatus;
    }
}
