/*
 * The MIT License (MIT)
 *
 * Copyright (c) liachmodded <https://github.com/liachmodded>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.liachmodded.uhcreloaded.forge.worldly;

import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.EntityEvent;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liach on 4/24/2016.
 *
 * @author liach
 */
public class ScopeManager {

    private boolean global = true;
    private List<Integer> worlds = new ArrayList<Integer>();
    private static final ScopeManager INSTANCE = new ScopeManager();

    private ScopeManager() {
    }

    public static ScopeManager getInstance() {
        return INSTANCE;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public void addWorld(World world) {
        addWorld(world.provider.getDimension());
    }

    public void addWorld(int dimId) {
        worlds.add(dimId);
    }

    public static boolean handle(EntityEvent event) {
        return INSTANCE.shouldHandle(event);
    }

    public boolean shouldHandle(EntityEvent event) {
        return isGameWorld(event.getEntity().worldObj);
    }

    public boolean isGameWorld(World world) {
        return world != null && isGameWorld(world.provider.getDimension());
    }

    public boolean isGameWorld(int id) {
        return global || worlds.contains(id);
    }

    public List<WeakReference<World>> getWorlds() {
        List<WeakReference<World>> ret = new ArrayList<WeakReference<World>>();
        for (int t : worlds) {
            ret.add(new WeakReference<World>(DimensionManager.getWorld(t)));
        }
        return ret;
    }

}
