package com.crawler.file;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.haier.market.common.consts.MathNumber;
import com.haier.market.common.utils.AesUtil;
import com.haier.market.pf.base.api.base.dto.*;
import com.haier.market.pf.base.ma.consts.JobFactory;
import com.haier.market.pf.base.ma.dto.JobHandleParamDTO;
import com.haier.market.pf.base.ma.entity.BaseCrowdPackEntity;
import com.haier.market.pf.base.ma.entity.PlanInfoEntity;
import com.haier.market.pf.base.ma.entity.UserInfoEntity;
import com.haier.market.pf.base.ma.enums.JobEnum;
import com.haier.market.pf.base.ma.mapper.PlanInfoMapper;
import com.haier.market.pf.base.ma.service.*;
import com.haier.market.pf.user.api.user.api.HiWorkAuthApi;
import com.haier.market.pf.user.api.user.dto.hworkTask.TaskToCommonUserDto;
import com.haier.market.pf.user.api.user.dto.hworkTask.UserInfoInDto;
import com.haier.uo.framework.web.entity.Result;
import com.haier.uo.framework.web.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@RestController
public class TestController {

    @Resource
    private HiWorkAuthApi hiWorkAuthApi;

    @Resource
    private FileService fileService;

    @Autowired
    private AesUtil aesUtil;

    @Resource
    private ButtedCDPService buttedCDPService;

    @Resource
    private BaseCrowdPackService baseCrowdPackService;

    @Resource
    private RecordOssService recordOssService;

    @Resource
    private PlanInfoMapper planInfoMapper;

    @PostMapping(value = "ma/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> test() {
        List<UserInfoInDto> userInfoInDtos = new ArrayList<>();
        UserInfoInDto userInfoInDto = new UserInfoInDto();
        userInfoInDto.setUserCode("22077640");
        userInfoInDtos.add(userInfoInDto);
        TaskToCommonUserDto task = new TaskToCommonUserDto();
        task.setTaskUserInfo(userInfoInDtos); // 通知人列表
        task.setSkipType(0);
        task.setRemark("发送效率每分钟xxx条，低于预期80%");
        task.setSystemSource("SP000169");
        task.setTaskDealType(0);
        task.setTaskStatus(0);
        task.setTaskTitle("MA消息队列预警");
        task.setTaskType(0);
        task.setTaskTypeEnum("to_common_user");
        task.setTaskWay(1);
        task.setClassifyType(6);
        task.setMsgSendType(0);
        task.setTaskClassifyCode("hwork-eymedi");
        Result<String> toCommonUserTask = hiWorkAuthApi.createToCommonUserTask(task);
        String data = toCommonUserTask.getData();
        System.out.println(data);

        JSONArray objects = JSON.parseArray(data);
        JSONObject jsonObject = objects.getJSONObject(0);

//        JSONObject jsonObject = JSON.parseObject(s);
        String taskId = (String) jsonObject.get("taskId");
        System.out.println(taskId);

        return ResultUtil.success("发送成功");
    }

    @PostMapping(value = "ma/generateBatchCode", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> generateBatchCode() {

        // 获取planId
        PlanInfoEntity planInfoEntity = planInfoMapper.selectById(139L);

        List<PlanBatchDTO> planBatchDTOList = new ArrayList<>();
        PlanBatchDTO planBatchDTO = new PlanBatchDTO();
        planBatchDTO.setPlanId(planInfoEntity.getId());
        planBatchDTO.setScheduleId(planInfoEntity.getScheduleId());
        planBatchDTO.setCrowdId(planInfoEntity.getCrowdId());
        planBatchDTO.setCrowdSubType(planInfoEntity.getMainCrowd());
        // 查询人群包
        BaseCrowdPackEntity entityByCrowdIdAndType = baseCrowdPackService.getEntityByCrowdIdAndType(planInfoEntity.getCrowdId(), planInfoEntity.getMainCrowd());
        if (ObjectUtil.isNull(entityByCrowdIdAndType)) {
            throw new RuntimeException(planInfoEntity.getPlanName() + "计划人群包不存在，请重新选择");
        }
        // 是否系统推荐
        if (MathNumber.ONE.equals(entityByCrowdIdAndType.getSuggestFlag())) {
            if (StringUtils.isNotEmpty(planInfoEntity.getSmallMicroJson()) && !"[]".equals(planInfoEntity.getSmallMicroJson())) {
                List<PermissionNodeDTO> list = JSONUtil.toList(JSONUtil.parseArray(planInfoEntity.getSmallMicroJson()), PermissionNodeDTO.class);
                List<String> microCodeList = list.stream().map(PermissionNodeDTO::getCode).collect(Collectors.toList());
                planBatchDTO.setMicroList(microCodeList);
            }
        }
        planBatchDTOList.add(planBatchDTO);

        CrowdScheduleDTO crowdScheduleDTO = new CrowdScheduleDTO();
        crowdScheduleDTO.setMaPlanList(planBatchDTOList);
        Boolean batchCode = buttedCDPService.getBatchCode(crowdScheduleDTO);
        System.out.println(batchCode);

        return ResultUtil.success("发送成功");
    }

    @PostMapping(value = "ma/getCrowdPackOSS", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> getCrowdPackOSS() {

        List<Long> planIds = new ArrayList<>();
        planIds.add(139L);
        PlanQueryOSSDTO planQueryOSSDTO = new PlanQueryOSSDTO();
        planQueryOSSDTO.setPlanIdList(planIds);
        List<CrowdPackOSSDTO> crowdPackOSS = buttedCDPService.getCrowdPackOSS(planQueryOSSDTO);
        System.out.println(crowdPackOSS);

        return ResultUtil.success("发送成功");
    }

    @PostMapping(value = "ma/handleJob", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> handleJob() {

        JobHandle handle = JobFactory.get(JobEnum.CREATE_USER_DATA.getCode());
        JobHandleParamDTO paramDTO = new JobHandleParamDTO();
        handle.handleJob(paramDTO);

        return ResultUtil.success("发送成功");
    }

    @PostMapping(value = "ma/cdp", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> cdp() {

        CDPCrowSizeDTO cdpCrowSizeDTO = new CDPCrowSizeDTO();
        cdpCrowSizeDTO.setCrowdId(186L);
        cdpCrowSizeDTO.setCrowdSubType(MathNumber.ZERO);
        String cdpCrowdSize = buttedCDPService.getCDPCrowdSize(cdpCrowSizeDTO);
        System.out.println(cdpCrowdSize);


        List<SuggestCategoryDTO> suggestCategory = buttedCDPService.getSuggestCategory();
        System.out.println(suggestCategory);

        return ResultUtil.success("发送成功");
    }

    @PostMapping(value = "ma/recordOss", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> recordOss() {

//        recordOssService.generateRecordOss();

        return ResultUtil.success("发送成功");
    }

    @PostMapping(value = "ma/deleteUserInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> deleteUserInfo() {

//        recordOssService.deleteUserInfo();

        return ResultUtil.success("发送成功");
    }

    @PostMapping(value = "ma/unZip", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> unZip() {

//        InputStream inputStream = fileService.readFile("https://rqqx-test-file.haier.net/cdp/test/ma_batch_13aea62ed7834a0691481eb89c73883e.zip");
//        InputStream inputStream = fileService.readFile("https://rqqx-test-file.haier.net/ma/test/ma_batch_13aea62ed7834a0691481eb89c73883e9e5073b0d00d42469c54f2a8a9e028b4.zip");
//        InputStream inputStream = fileService.readFile("https://rqqx-test-file.haier.net/cdp/test/ma_batch_6e985982311f4689b629b475f0d86df1.zip");
        InputStream inputStream = fileService.readFile("https://rqqx-test-file.haier.net/cdp/test/ma_batch_7eed7a102cdd4d14ab8c7e6679bf975a.zip");

        this.unzipInputStream(inputStream, 138L);
//        recordOssService.generateRecordOss(null);

        return ResultUtil.success("发送成功");
    }




    private List<List<UserInfoEntity>> unzipInputStream(InputStream zipInputStream, Long planId) {
        int count = 0;
        List<UserInfoEntity> userList = new ArrayList<>();
        List<List<UserInfoEntity>> userAllList = new ArrayList<>();
        try (ZipInputStream zip = new ZipInputStream(zipInputStream, Charset.forName("UTF-8"))) {
            ZipEntry zipEntry = null;
            BufferedInputStream bs = new BufferedInputStream(zip);
            byte[] bytes = null;
            while ((zipEntry = zip.getNextEntry()) != null) {
                String fileName_zip = zipEntry.getName();
                File file = new File(fileName_zip);
                if (fileName_zip.endsWith("/")) {
                    file.mkdir();
                    continue;
                } else {
//                    if ((int) zipEntry.getSize() <= 0) {
//                        // 读取完毕
//                        continue;
//                    }
//                    long size = zipEntry.getSize();
                    // 有的文件没有设置size,可能是-1
//                    if (size == -1) {
//                    if (true) {
//                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                        while (true) {
//                            int byteZip = zipInputStream.read();
//                            if (byteZip == -1) break;
//                            baos.write(byteZip);
//                        }
//                        bytes = baos.toByteArray();

//                        System.out.println(new String(bytes, "UTF-8"));

//
//                        int byteZip = zipInputStream.read();
//                        BufferedReader br1 = new BufferedReader(new InputStreamReader(byteArrayInputStream));
//                        bytes = new byte[byteZip];
//                        bs.read(bytes, 0 , byteZip);

//                        ByteArrayInputStream bInput = new ByteArrayInputStream(bytes);
//                        int c;
//                        while(( c= bInput.read())!= -1) {
//                            System.out.println(Character.toUpperCase((char)c));
//                        }
//                        bInput.reset();

//                        baos.close();





//                        int size1 = 0;
//                        byte[] buffer = new byte[1024];
//                        while (true) {
//                            size1 = zipInputStream.read(buffer, 0, buffer.length);
//                            if (size1 <= 0) {
//                                break;
//                            }
//                            baos.write(buffer, 0, size1);
//                        }
//                        baos.flush();
//                        baos.close();
//
//                        byte[] be = baos.toByteArray();
//                        String rtn = new String(be, "GBK");
//                        System.out.println(rtn);

//                        int byteZip = zip.read();
//                        bytes = new byte[byteZip];
//                        bs.read(bytes, 0, byteZip);
//
//                    } else {
//                        bytes = new byte[(int) zipEntry.getSize()];
//                        bs.read(bytes, 0, zip.read());
//                    }

//                    bytes = new byte[1024];
//                    if (bs.read(bytes, 0, (int) zipEntry.getSize()) != -1) {
//
//                    }

                    // 编码格式
//                    String charsetName = "GBK";//或GB2312，即ANSI
//                    if (bytes[0] == -1 && bytes[1] == -2 ) //0xFFFE
//                        charsetName = "UTF-16";
//                    else if (bytes[0] == -2 && bytes[1] == -1 ) //0xFEFF
//                        charsetName = "Unicode";//包含两种编码格式：UCS2-Big-Endian和UCS2-Little-Endian
//                    else if(bytes[0]==-27 && bytes[1]==-101 && bytes[2] ==-98)
//                        charsetName = "UTF-8"; //UTF-8(不含BOM)
//                    else if(bytes[0]==-17 && bytes[1]==-69 && bytes[2] ==-65)
//                        charsetName = "UTF-8"; //UTF-8-BOM
//                    System.out.println(charsetName);

                    // 转码
//                    String test = "测试";
//                    String test_gbk_utf8 = new String(test.getBytes(StandardCharsets.UTF_8), "gbk");
//                    System.out.println(test_gbk_utf8);//娴嬭瘯
//                    String test_utf8_gbk = new String(test_gbk_utf8.getBytes("gbk"), StandardCharsets.UTF_8);
//                    System.out.println(test_utf8_gbk);//测试


//                    InputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
//                    BufferedReader br = new BufferedReader(new InputStreamReader(byteArrayInputStream));
////                    BufferedReader br = new BufferedReader(new InputStreamReader(zipInputStream));
//                    String line = null;
//                    while ((line = br.readLine()) != null) {
//
//                        System.out.println(line);
////                        line = new String(line.getBytes("gbk"), StandardCharsets.UTF_8);
//
////                        byte[] utf8Bytes = line.getBytes("UTF-8");
////                        line = new String(utf8Bytes, "UTF-8");
////                        System.out.println(line);
//
//                        if (userList.size() >= 1000) {
//                            userAllList.add(userList);
//                            userList = new ArrayList<>();
//                        }
//                        if (StringUtils.isNotEmpty(line)) {
//                            UserInfoEntity userInfoEntity = this.handUserInfo(line, planId);
//                            if (userList.size() % 9 == 0) {
//                                // 10% 实验组
//                                userInfoEntity.setType(MathNumber.THREE);
//                            }
//                            userList.add(userInfoEntity);
//                            count++;
//                        }
//                    }
//                    br.close();




                    bytes = new byte[(int) zipEntry.getSize()];
                    bs.read(bytes, 0, (int) zipEntry.getSize());
                    InputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                    BufferedReader br = new BufferedReader(new InputStreamReader(byteArrayInputStream));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        if (userList.size() >= 1000) {
                            userAllList.add(userList);
                            userList = new ArrayList<>();
                        }
                        if (StringUtils.isNotEmpty(line)) {
                            UserInfoEntity userInfoEntity = this.handUserInfo(line, planId);
                            if (userList.size() % 9 == 0) {
                                // 10% 实验组
                                userInfoEntity.setType(MathNumber.THREE);
                            }
                            userList.add(userInfoEntity);
                            count++;
                        }
                    }
                    br.close();

                }
            }
            if (CollectionUtil.isNotEmpty(userList)) {
                userAllList.add(userList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userAllList;
    }

    private UserInfoEntity handUserInfo(String userInfo, Long planId) throws Exception {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        // 解密
        // 去掉换行符
//        userInfo.toString().replace("\r\n", "");
        String decrypt = aesUtil.decrypt(userInfo);
//        String decrypt = userInfo;
        String[] split = decrypt.trim().split(",");
        System.out.println(decrypt);

        // 组装数据
        if (split.length == 3) {
            userInfoEntity.setUserId(split[0]);
            userInfoEntity.setUserName(split[1]);
            // 加密
            userInfoEntity.setMobile(split[2]);
            userInfoEntity.setPlanId(planId);
            if (StringUtils.isEmpty(split[0]) || StringUtils.isEmpty(split[1])|| StringUtils.isEmpty(split[2])) {
                userInfoEntity.setState(MathNumber.THREE);
            } else {
                userInfoEntity.setState(MathNumber.TWO);
            }
            userInfoEntity.setType(MathNumber.TWO);
        } else {
            userInfoEntity.setUserId(split[0]);
            userInfoEntity.setState(MathNumber.THREE);
        }
        userInfoEntity.setCreateTime(new Date());
        userInfoEntity.setUpdateTime(new Date());
        userInfoEntity.setCreateBy("SYSTEM");
        userInfoEntity.setUpdateBy("SYSTEM");

        return userInfoEntity;
    }

}
