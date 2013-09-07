package com.campus.prime.protocol;

import java.util.List;

import com.campus.prime.model.ModelBase;

public interface ProtocolDelegate<T extends ModelBase> {
	public void getMessageSuccess(List<T> list);
	public void getMessageFailed();
}
