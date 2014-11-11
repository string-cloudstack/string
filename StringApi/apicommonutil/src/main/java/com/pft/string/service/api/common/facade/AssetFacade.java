package com.pft.string.service.api.common.facade;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.pft.string.service.api.common.bdo.Assets;
import com.pft.string.service.api.common.repositories.AssetsRepository;
import com.pft.string.service.framework.business.base.actions.CreateAction;
import com.pft.string.service.framework.business.base.actions.DeleteAction;
import com.pft.string.service.framework.business.base.actions.EntityAction;
import com.pft.string.service.framework.business.base.actions.UpdateAction;
import com.pft.string.service.framework.data.repositories.Repository;

public class AssetFacade {

	public class CreateAssets extends CreateAction<Assets> {
		public CreateAssets() {
			super(CreateAssets.class);
		}

		@Override
		protected void OnExecute() throws Exception {
			super.OnExecute();
		}
	}
	
	public class DeleteAssets extends DeleteAction<Assets> {
		public DeleteAssets() {
			super(DeleteAssets.class);
		}
		
		@Override
		protected void OnExecute() throws Exception {
			new Repository<Assets>(getEntity().getClass()).delete(getEntity());
		}
	}
	
	public class UpdateAsset extends UpdateAction<Assets> {
		public UpdateAsset() {
			super(UpdateAsset.class);
		}
		
		@Override
		protected void OnExecute() throws Exception {
			super.OnExecute();
		}
	}

	public class SearchAsset extends EntityAction<Assets>{
		private String assetHash;
		
		public void setAssetHash(String assetUUID)
		{
			assetHash=assetUUID;
		}
		
		public String getAssetHash()
		{
			return assetHash;
		}
		
		public SearchAsset()
		{
			super(SearchAsset.class);
		}
		
		@Override
		protected void OnExecute() throws Exception { 
			Criterion searchQuery=null;
			searchQuery=Restrictions.eq("assetHash", getAssetHash());
			DetachedCriteria criteria=DetachedCriteria.forClass(Assets.class).add(searchQuery);
			this.setEntity((Assets) new AssetsRepository().findOne(criteria));
		}
	}
	public class GetAllAssets extends EntityAction<List<Assets>>{
		private String assetHash;
		
		public void setAssetHash(String assetUUID){
			this.assetHash=assetUUID;
		}
		
		public String getAssetHash(){
			return assetHash;
		}
		
		public GetAllAssets(){
			super(GetAllAssets.class);
		}
		
		@Override
		protected void OnExecute() throws Exception { 
			Criterion searchQuery=null;
			searchQuery=Restrictions.eq("assetHash", getAssetHash());
			DetachedCriteria criteria=DetachedCriteria.forClass(Assets.class).add(searchQuery);
			this.setEntity((List<Assets>) new AssetsRepository().findAll(criteria));
		}
	}
	
}