/*  This file is part of UltraHardcore: Reloaded (abbr. UHCR in following context)
 *  Copyright (C) 2015- 3TUSK
 *
 *  UHCR is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  UHCR is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with UHCR.  If not, see <http://www.gnu.org/licenses/>.
 */
package mod.uhcreloaded.fecompat;

import com.forgeessentials.api.APIRegistry;
import com.forgeessentials.core.moduleLauncher.FEModule;
import mod.uhcreloaded.UhcReloaded;
import mod.uhcreloaded.util.Misc;

/**
 * Created by liach on 9/13/2015.
 *
 * @author liach
 */
@FEModule(name = Misc.MODID, parentMod = UhcReloaded.class)
public class UhcFeModule {

    @FEModule.Instance
    public UhcFeModule instance;

    public UhcFeModule() {
        instance = this;
    }
}
