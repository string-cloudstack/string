package com.pft.string.service.framework.data.types;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;


import com.pft.string.service.framework.core.types.PagingCriteria;

public class CriteriaHelper
{
	public DetachedCriteria Build(Class classInfo,PagingCriteria pagingCriteria,Long userId)
	{
		 DetachedCriteria criteria = null;
	    
		 if(classInfo != null)
		criteria = DetachedCriteria.forClass(classInfo);
	
		if (pagingCriteria != null)
        {                       
                         
            criteria.add(Restrictions.eq("isDeleted", false));

            if (pagingCriteria.getSpecificToUser())
            {
                criteria = criteria.add(Restrictions.eq("createdBy", userId));
            }
            if (pagingCriteria.getDateFrom() != null)
            {
                criteria = criteria.add(Restrictions.between("createdOn", pagingCriteria.getDateFrom().getValue(), pagingCriteria.getDateTo().getValue()));
            }
         
	
		
	   }
		return criteria;
		
	}

}
