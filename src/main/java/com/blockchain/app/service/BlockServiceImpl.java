package com.blockchain.app.service;

import com.blockchain.app.entity.Block;
import com.blockchain.app.entityDto.BlockDto;
import com.blockchain.app.repository.BlockRepository;
import com.blockchain.app.responses.BlockResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlockServiceImpl implements BlockService{

    @Autowired
    private BlockRepository blockRepository;

    private ModelMapper modelMapper=new ModelMapper();

    @Override
    public BlockDto create_block(BlockDto blockDto) {
        List<BlockDto> chain = get_chain();
        Block newBlock = modelMapper.map(blockDto, Block.class);
        newBlock.setNonce(getNonce(chain));
        newBlock.setTimestamp(new Date().toString());
        newBlock.setIndexBlock(Long.valueOf(chain.size()+1));
        newBlock.setPreviousHash(get_previous_block(chain).getCurrent_hash());
        String newBlockToString = newBlock.toString();
        String hash=DigestUtils.sha256Hex(newBlockToString);
        newBlock.setCurrent_hash(hash);
        Block blockSaved = blockRepository.save(newBlock);
        BlockDto blockDtoCreated = modelMapper.map(blockSaved, BlockDto.class);
        return blockDtoCreated;
    }

    @Override
    public List<BlockDto> get_chain() {
        List<Block> blockList = blockRepository.findAll();
        if(blockList.size()==0){
            Block genesisBlock = create_genesisBlock();
            blockList.add(genesisBlock);
        }
        List<BlockDto> blockDtoList=new ArrayList<BlockDto>();
        for(Block block:blockList){

            BlockDto blockDto = modelMapper.map(block, BlockDto.class);
            blockDtoList.add(blockDto);
        }
        return blockDtoList;
    }



    public BlockDto get_previous_block(List<BlockDto> blockDtoList){
        BlockDto blockDto = blockDtoList.get(blockDtoList.size() - 1);
        return blockDto;
    }



    public Long getNonce(List<BlockDto> blockDtoList){
        Long nonce=new Long(1);
        String condition_proof="0000";
        Long previous_nonce=get_previous_block(blockDtoList).getNonce();
        boolean condition_proof_isverified=false;
        while(condition_proof_isverified==false){

            String hash = DigestUtils.sha256Hex(String.valueOf((Math.pow(nonce, 2) - previous_nonce)));
            String x=hash.substring(0,4);
            System.out.println(x);
            System.out.println(Math.pow(nonce, 2) - previous_nonce);
            if (hash.substring(0,4).equals(condition_proof)){
                condition_proof_isverified=true;
            }
            nonce+=1;
        }
        return nonce;

    }


    public Block create_genesisBlock(){
        Block genesisBlock=new Block();
        genesisBlock.setIndexBlock(new Long(1));
        genesisBlock.setData("Initialisation .....");
        genesisBlock.setPreviousHash("0000000000000000000000000000000000000000000000000000000000000000");
        genesisBlock.setTimestamp(new Date().toString());
        genesisBlock.setCurrent_hash("0000000000000000000000000000000000000000000000000000000000000000");
        genesisBlock.setNonce(new Long(1));
        Block block = blockRepository.save(genesisBlock);
        return block;
    }


}
