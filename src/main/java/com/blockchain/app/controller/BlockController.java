package com.blockchain.app.controller;

import com.blockchain.app.entityDto.BlockDto;
import com.blockchain.app.requests.BlockRequest;
import com.blockchain.app.responses.BlockResponse;
import com.blockchain.app.service.BlockService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/block")
public class BlockController {

    @Autowired
    private BlockService blockService;

    private ModelMapper modelMapper=new ModelMapper();
    

    @PostMapping(value ="/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BlockResponse> create_block(@RequestBody BlockRequest blockRequest){

        BlockDto blockDto = modelMapper.map(blockRequest, BlockDto.class);
        BlockDto blockcreated = blockService.create_block(blockDto);
        BlockResponse blockResponse = modelMapper.map(blockcreated, BlockResponse.class);
        return new ResponseEntity<>(blockResponse, HttpStatus.CREATED);
    }


    @GetMapping(value="/getchain",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BlockResponse>> get_chain(){
        List<BlockDto> chain = blockService.get_chain();
        List<BlockResponse> blockResponseList=new ArrayList<BlockResponse>();
        for(BlockDto blockDto:chain){

            BlockResponse blockResponse = modelMapper.map(blockDto, BlockResponse.class);
            blockResponseList.add(blockResponse);
        }
        return new ResponseEntity<>(blockResponseList, HttpStatus.OK);
    }
}
