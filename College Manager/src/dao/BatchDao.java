package dao;

import java.util.List;

import exception.BatchException;
import model.Batch;
import model.Report;

public interface BatchDao {

    public String addBatch(Batch batch) throws BatchException;

    public Batch searchBatchById(String id) throws BatchException;

    public List<Batch> searchBatchByName(String name) throws BatchException;

    public List<Batch> allBatch() throws BatchException;

    public String updateBatch (String str, String set, String batchId) throws BatchException;

    public String deleteBatch(String batchId) throws BatchException;

    public String allocateFaculty(int fName, String batchId) throws BatchException;

    public List<Report> generateReport() throws BatchException;
}
