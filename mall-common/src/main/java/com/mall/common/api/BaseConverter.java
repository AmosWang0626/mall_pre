package com.mall.common.api;

import com.mall.common.pojo.dao.BaseEntity;
import com.mall.common.pojo.response.BaseVO;
import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 模块名称: mall
 * 模块描述: BaseConverter
 * 1. convert: VO >>> Entity
 * 2. reduction: Entity >>> VO
 * <br>
 * 扩展 List/Set 集合统一转换
 * 1.1 convertToList: List<VO> >>> List<Entity>
 * 1.2 convertToSet: Set<VO> >>> Set<Entity>
 * <br>
 * 2.1 reductionToList: List<Entity> >>> List<VO>
 * 2.2 reductionToSet: Set<Entity> >>> Set<VO>
 *
 * @author amos.wang
 * @date 2020/4/8 17:32
 */
public interface BaseConverter {

    Map<String, Method> CACHED_METHOD = new ConcurrentHashMap<>(32);

    /**
     * convert [VO >>> Entity]
     *
     * @param vo       VO
     * @param <VO>     BaseVO
     * @param <Entity> BaseEntity
     * @return Entity
     */
    @SneakyThrows
    default <VO extends BaseVO, Entity extends BaseEntity> Entity convert(VO vo) {
        String key = vo.getClass().getSimpleName();
        Method method = CACHED_METHOD.get(key);
        if (method == null) {
            synchronized (CACHED_METHOD) {
                method = this.getClass().getDeclaredMethod("convert", vo.getClass());
                CACHED_METHOD.put(key, method);
            }
        }

        return (Entity) method.invoke(this, vo);
    }

    /**
     * reduction [Entity >>> VO]
     *
     * @param entity   Entity
     * @param <VO>     BaseVO
     * @param <Entity> BaseEntity
     * @return VO
     */
    @SneakyThrows
    default <VO extends BaseVO, Entity extends BaseEntity> VO reduction(Entity entity) {
        String key = entity.getClass().getSimpleName();
        Method method = CACHED_METHOD.get(key);
        if (method == null) {
            synchronized (CACHED_METHOD) {
                method = this.getClass().getDeclaredMethod("reduction", entity.getClass());
                CACHED_METHOD.put(key, method);
            }
        }

        return (VO) method.invoke(this, entity);
    }

    /**
     * convertToList [List<VO> >>> List<Entity>]
     *
     * @param voList   List<VO>
     * @param <VO>     BaseVO
     * @param <Entity> BaseEntity
     * @return List<Entity>
     */
    default <VO extends BaseVO, Entity extends BaseEntity> List<Entity> convertToList(List<VO> voList) {
        if (voList != null) {
            return voList.stream().map(vo -> (Entity) this.convert(vo)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * reductionToList [List<Entity> >>> List<VO>]
     *
     * @param entityList List<Entity>
     * @param <VO>       BaseVO
     * @param <Entity>   BaseEntity
     * @return List<VO>
     */
    default <VO extends BaseVO, Entity extends BaseEntity> List<VO> reductionToList(List<Entity> entityList) {
        if (entityList != null) {
            return entityList.stream().map(entity -> (VO) this.reduction(entity)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * convertToSet [Set<VO> >>> Set<Entity>]
     *
     * @param voSet    Set<VO>
     * @param <VO>     BaseVO
     * @param <Entity> BaseEntity
     * @return Set<Entity>
     */
    default <VO extends BaseVO, Entity extends BaseEntity> Set<Entity> convertToSet(Set<VO> voSet) {
        if (voSet != null) {
            return voSet.stream().map(vo -> (Entity) this.convert(vo)).collect(Collectors.toSet());
        }
        return new HashSet<>();
    }

    /**
     * reductionToSet [Set<Entity> >>> Set<VO>]
     *
     * @param entitySet Set<Entity>
     * @param <VO>      BaseVO
     * @param <Entity>  BaseEntity
     * @return Set<VO>
     */
    default <VO extends BaseVO, Entity extends BaseEntity> Set<VO> reductionToSet(Set<Entity> entitySet) {
        if (entitySet != null) {
            return entitySet.stream().map(entity -> (VO) this.reduction(entity)).collect(Collectors.toSet());
        }
        return new HashSet<>();
    }

}
