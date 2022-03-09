package com.wwinfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wwinfo.model.Asset;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wwinfo.pojo.dto.AssetDestoryParam;
import com.wwinfo.pojo.dto.AssetQueryParam;
import com.wwinfo.pojo.dto.AssetReturnVParam;
import com.wwinfo.pojo.dto.BlackListVParam;
import com.wwinfo.pojo.query.AssetQuery;
import com.wwinfo.pojo.vo.AssetAddVO;
import com.wwinfo.pojo.vo.AssetChgVO;
import com.wwinfo.pojo.vo.BindRFIDVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 资产表 服务类
 * </p>
 *
 * @author lk
 * @since 2022-02-14
 */
public interface AssetService extends IService<Asset> {

    /**
     * 分页获取资产
     * @param assetQuery
     * @return
     */
    IPage listPage(AssetQuery assetQuery);

    /**
     * 添加资产
     * @param assetAddVO
     * @return
     */
    int add(AssetAddVO assetAddVO);

    /**
     * 编辑资产
     * @param assetChgVO
     * @return
     */
    int update(AssetChgVO assetChgVO);

    /**
     * 资产excel导入
     * @param file
     * @return
     */
    int uploadExcel(MultipartFile file);

    /**
     * 删除资产
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 销毁资产
     * @param assetDestoryParam
     * @return
     */
    int destroy(AssetDestoryParam assetDestoryParam);

    /**
     * 资产归还
     * @param assetReturnVParam
     * @return
     */
    int assetReturn(AssetReturnVParam assetReturnVParam);

    /**
     * 资产黑名单设置
     * @param blackListVParam
     * @return
     */
    int setBlackList(BlackListVParam blackListVParam);

    /**
     *
     * @param response
     * @param assetQueryParam
     */
    void exportExcel(HttpServletResponse response, AssetQueryParam assetQueryParam);

    /**
     * 导出excel
     * @param response
     * @param assetQueryParam
     */
    void exportCustomExcel(HttpServletResponse response, AssetQueryParam assetQueryParam);

    /**
     * 分批导入
     * @param assetList
     */
    void batchImport(List<Asset> assetList, int count);

    /**
     * 资产RFID绑定
     * @param bindRFIDVO
     * @return
     */
    int bindRFID(BindRFIDVO bindRFIDVO);

    /**
     * 获取所有的资产的RFIDrecord
     * @return
     */
    List<HashMap<String, Object>> getAllRFIDRecord();

}
