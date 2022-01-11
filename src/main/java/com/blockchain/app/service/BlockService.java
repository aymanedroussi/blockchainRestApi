package com.blockchain.app.service;

import com.blockchain.app.entityDto.BlockDto;

import java.util.List;

public interface BlockService {

    public BlockDto create_block(BlockDto blockDto);
    public List<BlockDto> get_chain();
}
