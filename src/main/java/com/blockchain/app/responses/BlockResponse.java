package com.blockchain.app.responses;

public class BlockResponse {
    private Long indexBlock;


    private String timestamp;

    private Long nonce;

    private String data;

    private String previousHash;

    private String current_hash;

    public Long getIndexBlock() {
        return indexBlock;
    }

    public void setIndexBlock(Long indexBlock) {
        this.indexBlock = indexBlock;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Long getNonce() {
        return nonce;
    }

    public void setNonce(Long nonce) {
        this.nonce = nonce;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getCurrent_hash() {
        return current_hash;
    }

    public void setCurrent_hash(String current_hash) {
        this.current_hash = current_hash;
    }
}
