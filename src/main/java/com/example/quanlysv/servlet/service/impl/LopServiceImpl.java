package com.example.quanlysv.servlet.service.impl;

import com.example.quanlysv.servlet.common.Constant;
import com.example.quanlysv.servlet.dao.ILopDao;
import com.example.quanlysv.servlet.dao.impl.AbstractDao;
import com.example.quanlysv.servlet.dao.impl.LopDaoImpl;
import com.example.quanlysv.servlet.dto.request.BaseRequest;
import com.example.quanlysv.servlet.dto.request.lop.CreateOrEditLopDTO;
import com.example.quanlysv.servlet.dto.request.lop.LopDTO;
import com.example.quanlysv.servlet.dto.request.lop.LopFilter;
import com.example.quanlysv.servlet.dto.response.BaseResponse;
import com.example.quanlysv.servlet.entity.LopEntity;
import com.example.quanlysv.servlet.mapper.LopMapper;
import com.example.quanlysv.servlet.service.ILopService;
import com.example.quanlysv.servlet.util.Convert;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LopServiceImpl implements ILopService {
    private static final org.apache.log4j.Logger log = Logger.getLogger(StudentServiceImpl.class.getName());

    private ILopDao lopDao;

    public LopServiceImpl(){
        lopDao=new LopDaoImpl();
    }
    private final Date date = new Date();


    @Override
    public BaseResponse<?> createOrUpdateLop(CreateOrEditLopDTO lopDTO) {
        try{
            if(lopDTO.getStatus() !=0 && lopDTO.getStatus()!=1){
                return new BaseResponse.Builder<>()
                        .setStatus(Constant.httpStatusErrorServer)
                        .setMessage(Constant.messageBadRequest)
                        .build();
            }

            if(lopDTO.getStatus()==0){
                if(lopDao.existedByIdAndTenLop(lopDTO.getIdLop(),lopDTO.getTenLop())){
                    return new BaseResponse.Builder<>()
                            .setStatus(Constant.httpStatusErrorServer)
                            .setMessage(Constant.messageStudentExist)
                            .build();
                }
            }

            LopEntity lopEntity = Convert.convertDTOToEntity(lopDTO,LopEntity.class);
            if(lopEntity != null){
                Instant timeNow = Instant.now();
                lopEntity.setNgayTao(timeNow);
                lopEntity.setNgaySua(timeNow);
                lopDao.createOrUpdateLop(lopEntity);
                return new BaseResponse.Builder<>()
                        .setStatus(Constant.httpStatusOk)
                        .setMessage(Constant.messageSuccess).build();
            }
            return new BaseResponse.Builder<>()
                    .setStatus(Constant.httpStatusErrorServer)
                    .setMessage(Constant.messageFailed).build();
        }catch (IllegalAccessException e) {
            log.error("failed by =>", e.getCause());
            return new BaseResponse.Builder<>()
                    .setStatus(Constant.httpStatusErrorServer)
                    .setMessage(Constant.messageFailed).build();
        } catch (InstantiationException e) {
            log.error("failed by =>", e.getCause());
            return new BaseResponse.Builder<>()
                    .setStatus(Constant.httpStatusErrorServer)
                    .setMessage(Constant.messageFailed).build();
        }
    }

    @Override
    public BaseResponse<?> findLop(LopFilter request) {
        try{
            if((request.getBaseRequest().getSortOrder() == null || request.getBaseRequest().getSortOrder().isEmpty())) {
                request.getBaseRequest().setSortOrder("asc");
            }
            if(request.getBaseRequest().getSortField() == null || request.getBaseRequest().getSortField().isEmpty()){
                request.getBaseRequest().setSortField("id_lop");
            }


            List<LopDTO>dtoList=new ArrayList<>();
            List<LopEntity>list=lopDao.findLop(request);
            Integer totalRecords = lopDao.countTotalRecords(request);
            int totalPages = totalRecords!= null?(int) Math.ceil((double)
                    totalRecords / request.getBaseRequest().getPageSize()): null;
            dtoList=list.stream().map(x->{
                try {
                    return Convert.convertEntityToDTO(x,LopDTO.class);
                }catch (IllegalAccessException e){
                    System.out.println(e.getCause());
                    return null;
                }catch (InstantiationException e){
                    System.out.println(e.getCause());
                    return null;
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }).filter(Objects::nonNull).collect(Collectors.toList());

            return new BaseResponse.Builder<List<LopDTO>>()
                    .setData(dtoList).setMessage("success").setStatus(200).setTotalPages(totalPages).build();
        }catch (Exception e){
            return new BaseResponse.Builder<List<LopDTO>>()
                    .setMessage("failed"+e.getMessage())
                    .setStatus(500)
                    .build();
        }
    }

    @Override
    public BaseResponse<?> deleteLopById(String id) {
        try{
            lopDao.deleteLopById(id);
            return new BaseResponse.Builder<List<LopDTO>>()
                    .setMessage("success")
                    .setStatus(200)
                    .build();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new BaseResponse.Builder<List<LopDTO>>()
                    .setMessage("failed"+e.getMessage()).setStatus(500).build();
        }
    }

    @Override
    public BaseResponse<?> getLopByIdKhoa(String idKhoa) {
        try{
            List<LopDTO>dtoList=new ArrayList<>();
            List<LopEntity>list=lopDao.getListLopByIdKhoa(idKhoa);

            dtoList=list.stream().map(x->{
                try {
                    return Convert.convertEntityToDTO(x,LopDTO.class);
                }catch (IllegalAccessException e){
                    System.out.println(e.getCause());
                    return null;
                }catch (InstantiationException e){
                    System.out.println(e.getCause());
                    return null;
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }).filter(Objects::nonNull).collect(Collectors.toList());

            return new BaseResponse.Builder<List<LopDTO>>()
                    .setData(dtoList)
                    .setMessage("success")
                    .setStatus(200).build();
        }catch (Exception e){
            return new BaseResponse.Builder<List<LopDTO>>()
                    .setMessage("failed"+e.getMessage())
                    .setStatus(500)
                    .build();
        }
    }

    @Override
    public List<LopEntity> getAllLop() {
        return lopDao.getAllLop();
    }
}