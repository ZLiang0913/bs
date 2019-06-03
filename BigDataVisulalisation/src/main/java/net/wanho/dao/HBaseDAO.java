package net.wanho.dao;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;

import java.lang.reflect.Constructor;
import java.util.List;

public interface HBaseDAO<T> {

	public void save(Put put, String tableName) ;
	public void insert(String tableName, String rowKey, String family, String quailifer, String value) ;
	public void insert(String tableName, String rowKey, String family, String quailifer[], String value[]) ;
	
	public void save(List<Put> Put, String tableName) ;
	
	public Result getOneRow(String tableName, String rowKey) ;
	
	public List<Result> getRows(String tableName, String rowKey_like) ;
	
	public List<Result> getRows(String tableName, String rowKeyLike, String family, String cols[]) ;

	public List<Result> getRows(String tableName, String startRow, String stopRow) ;

	public List<T> getScan(String tableName, String colFamilyName, String colName, Constructor<T> constructor);
	
	public void deleteRecords(String tableName, String rowKeyLike);
}
