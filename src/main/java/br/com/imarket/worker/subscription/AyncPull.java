package br.com.imarket.worker.subscription;

public interface AyncPull<T> {

	void received(T t);
}
