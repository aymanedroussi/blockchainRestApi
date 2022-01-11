package com.blockchain.app.entity;

import javax.persistence.*;

@Entity
@Table(name="block")
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long indexBlock;

    @Column
    private String timestamp;

    @Column
    private Long nonce;

    @Column
    private String data;

    @Column
    private String previousHash;

    @Column
    private String current_hash;


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIndexBlock() {
        return indexBlock;
    }

    public void setIndexBlock(Long indexBlock) {
        this.indexBlock = indexBlock;
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
