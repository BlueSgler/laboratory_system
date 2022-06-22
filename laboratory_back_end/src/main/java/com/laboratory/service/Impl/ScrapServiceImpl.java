package com.laboratory.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laboratory.entity.scrap.Scrap;
import com.laboratory.mapper.ScrapMapper;
import com.laboratory.service.ScrapService;
import com.laboratory.util.result.CommonResult;
import com.laboratory.util.result.PageResult;
import com.laboratory.util.result.ResultCode;
import com.laboratory.util.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScrapServiceImpl implements ScrapService {
    private static final Long PAGE_SIZE = 10L;

    @Autowired
    ScrapMapper scrapMapper;

    @Override
    public CommonResult<Scrap> selectOneById(String id) {
        Scrap scrapWeb = scrapMapper.selectById(id);
        if (scrapWeb == null) {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("没有id为" + id + "的用户");
            return fail;
        } else {
            return ResultUtil.success(scrapWeb);
        }
    }

    @Override
    public CommonResult<PageResult<Scrap>> selectByPage(String currentPage, QueryWrapper<Scrap> queryWrapper) {
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        Page<Scrap> page = new Page<>(Long.parseLong(currentPage), PAGE_SIZE);
        scrapMapper.selectPage(page, queryWrapper);
        PageResult<Scrap> userWebList = new PageResult<>(page);
        if (userWebList.hasRecords()) {
            return ResultUtil.success(userWebList);
        } else {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("无记录");
            return fail;
        }
    }

    @Override
    public CommonResult<PageResult<Scrap>> selectByCondition(String currentPage, String type, String text) {
        QueryWrapper<Scrap> queryWrapper = new QueryWrapper<>();
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if ("id".equals(type)) {
            queryWrapper.eq("id", text);
        } else if ("createTime".equals(type)) {
            queryWrapper.like("create_time", text);
        } else if ("updateTime".equals(type)) {
            queryWrapper.like("update_time", text);
        }
        else if ("scrapTime".equals(type)) {
            queryWrapper.like("scrap_time", text);
        }else if ("name".equals(type)) {
            queryWrapper.like("name", text);
        }
        else if ("category".equals(type)) {
            queryWrapper.like("category", text);
        }
        else if ("specification".equals(type)) {
            queryWrapper.like("specification", text);
        }
        return selectByPage(currentPage, queryWrapper);
    }

    @Override
    public CommonResult deleteOneById(String id) {
        int delete = scrapMapper.deleteById(id);
        if (delete == 0) {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("没有id为" + id + "的商品");
            return fail;
        } else {
            return ResultUtil.success();
        }
    }

    @Override
    public CommonResult deleteBatchById(String[] id) {
        for (String s : id) {
            int delete = scrapMapper.deleteById(s);
            if (delete == 0) {
                CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
                return fail;
            }
        }
        return ResultUtil.success();
    }


    @Override
    public CommonResult insertOneUser(Scrap scrapWeb) {
        int insert = scrapMapper.insert(scrapWeb);
        if (insert == 1) {
            return ResultUtil.success();
        } else {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("插入用户信息失败");
            return fail;
        }
    }

    @Override
    public CommonResult updateOneUser(Scrap scrapWeb) {
        if (scrapMapper.updateById(scrapWeb) == 1) {
            return ResultUtil.success();
        } else {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("没有id为" + scrapWeb.getId() + "的用户");
            return fail;
        }
    }
}
