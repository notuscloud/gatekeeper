package fr.notuscloud.meetup.gatekeeper.elasticsearch;

import org.springframework.stereotype.Service;

@Service
public class DecryptRecordService {

    private DecryptRecordDao decryptRecordDao;
    // Constructor
    public DecryptRecordService(DecryptRecordDao decryptRecordDao) {
        this.decryptRecordDao = decryptRecordDao;
    }

    public String indexDecryptRecord(DecryptRecord decryptRecord){
        return decryptRecordDao.indexDecryptRecord(decryptRecord);
    }
}
