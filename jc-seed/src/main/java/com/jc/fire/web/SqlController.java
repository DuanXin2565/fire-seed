package com.jc.fire.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jc.fire.dao.AreaDao;
import com.jc.fire.dao.AreasDao;
import com.jc.fire.dao.OrganizationDao;
import com.jc.fire.dao.OrgsDao;
import com.jc.fire.dao.RelaDao;
import com.jc.fire.dao.RelationalDao;
import com.jc.fire.model.Area;
import com.jc.fire.model.Areas;
import com.jc.fire.model.Organization;
import com.jc.fire.model.Orgs;
import com.jc.fire.model.Rela;
import com.jc.fire.model.Relational;
import com.jc.fire.model.request.PageRequest;
import io.swagger.annotations.ApiOperation;

/**
 * <Description> <br>
 *
 * @author duan.xin<br>
 * @version 1.0<br>
 * @taskId <br>
 * @Date 2020/9/8 9:21
 * @see com.jc.fire.web <br>
 * @since V0.1<br>
 */
@RestController
@RequestMapping("/sql")
public class SqlController {

    @Resource
    AreasDao areasDao;
    @Resource
    AreaDao areaDao;
    @Resource
    OrgsDao orgsDao;
    @Resource
    OrganizationDao organizationDao;

    @Resource
    RelationalDao relationalDao;
    @Resource
    RelaDao relaDao;

    private final Logger logger = LoggerFactory.getLogger(SqlController.class);

    /**
     * 已经签了1000条,pageNum接下来该是11
     * @param pageRequest
     * @return
     */
    @ApiOperation(value = "区域迁移", notes = "区域迁移")
    @PostMapping("/area/add")
    public void insertToOtherArea(PageRequest pageRequest) {
        //获取开始时间
        long startTime = System.nanoTime();

        List<Areas> areasList = areasDao.selectAreas(pageRequest);
        long secondTime = System.nanoTime();
        logger.info("程序运行时间： " + (secondTime - startTime) + "ns");
        List<Area> areaList = new ArrayList<>();
        Area area;
        if (areasList.size() > 0) {
            for (Areas areas : areasList) {
                area = new Area();
                area.setAreaId(areas.getId());
                area.setAreaType(areas.getLevel().toString());
                area.setAreaName(areas.getName());
                area.setParentId(areas.getParentId());
                area.setIsDelete(false);
                area.setStateDate(new Date());
                areaList.add(area);
//                areaDao.insert(area);

            }
            areaDao.batchInsert(areaList);
        }
        //获取结束时间
        long endTime = System.nanoTime();
        logger.info("程序运行时间： " + (endTime - startTime) + "ns");
    }


    @ApiOperation(value = "部门迁移", notes = "部门迁移")
    @PostMapping("/org/add")
    public int insertToOtherOrganization(PageRequest pageRequest) {
        List<Orgs> orgsList = orgsDao.queryOrgs(pageRequest);
        List<Organization> organizationList = new ArrayList<>();
        Organization organization;
        if (orgsList.size() > 0) {
            for (Orgs orgs : orgsList) {
                organization = new Organization();
                organization.setStateDate(new Date());
                organization.setAreaId(orgs.getAreaId());
                organization.setOrgId(orgs.getId());
                organization.setOrgName(orgs.getName());
                organization.setOrgType(orgs.getType().toString());
                organization.setIsDelete(orgs.getIsDeleted());
                organization.setLevel(orgs.getLevel().toString());
                organization.setIsEnable(orgs.getCanUse());
                organization.setPhone(orgs.getSupportCall());
                organization.setParentId(orgs.getParentId());
                //目前源数据库的orgs暂时不存在address详细地址字段，后续可能会补，记得要添加地址
//                organizationDao.insert(organization);
                organizationList.add(organization);
            }
            organizationDao.batchInsert(organizationList);
        }
        return 0;
    }

    @ApiOperation(value = "关系迁移", notes = "关系迁移")
    @PostMapping("/rela/add")
    public int insertToOtherRelation(PageRequest pageRequest) {
        List<Relational> relationalList = relationalDao.queryRelation(pageRequest);
        List<Rela> relaList = new ArrayList<>();
        if (relationalList.size() > 0) {
            Rela rela;
            for (Relational relational : relationalList) {
                rela = new Rela();
                rela.setFireDeptId(relational.getFridageId());
                rela.setRelaId(relational.getId());
                rela.setWaterDeptId(relational.getOrgId());
//                relaDao.insert(rela);
                relaList.add(rela);
            }
            relaDao.batchInsert(relaList);
        }
        return 0;
    }
}
