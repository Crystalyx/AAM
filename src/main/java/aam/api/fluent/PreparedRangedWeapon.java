package aam.api.fluent;

import aam.api.abstraction.RangedWeapon;
import aam.common.items.ModItems;
import aam.utils.EnumRarity;
import net.minecraft.item.Item;

public class PreparedRangedWeapon
{
    public float meleeDmg = 3;
    public float rangedDamage = 7;
    public int soulConsumed = 2;
    public int cooldown = 20;

    public int minSlots = 0;
    public int maxSlots = 0;
    public boolean fixedSlots = false;
    public EnumRarity rarity = EnumRarity.Common;
    public int repairs = 3;
    public int durability = 500;
    public Item repairItem = null;
    public int repairItemMeta = 0;
    public double knockback = 0;
    public float reach = 4;

    private float baseDamage = 5;
    private boolean bypassesArmor = false;
    public String textureName = "";
    public String name = "";

    public PreparedRangedWeapon withSlotsRanged(int _min, int _max)
    {
        this.minSlots = _min;
        this.maxSlots = _max;
        this.fixedSlots = false;
        return this;
    }

    public PreparedRangedWeapon withSlotsFixed(int _slots)
    {
        this.maxSlots = _slots;
        this.fixedSlots = true;
        return this;
    }

    public PreparedRangedWeapon withRarity(EnumRarity _rar)
    {
        this.rarity = _rar;
        return this;
    }

    public PreparedRangedWeapon withRepairs(int _rep)
    {
        this.repairs = _rep;
        return this;
    }

    public PreparedRangedWeapon withDurability(int _dur)
    {
        this.durability = _dur;
        return this;
    }

    public PreparedRangedWeapon withCooldown(int _cooldown)
    {
        this.cooldown = _cooldown;
        return this;
    }

    public PreparedRangedWeapon withSoulConsumed(int _soulConsumed)
    {
        this.soulConsumed = _soulConsumed;
        return this;
    }

    public PreparedRangedWeapon withRepairItem(Item _item, int _meta)
    {
        this.repairItem = _item;
        this.repairItemMeta = _meta;
        return this;
    }

    public PreparedRangedWeapon withKnockback(double _knock)
    {
        this.knockback = _knock;
        return this;
    }

    public PreparedRangedWeapon withReach(int _reach)
    {
        this.reach = _reach;
        return this;
    }

    public PreparedRangedWeapon withMeleeDamage(float _meleeDamage)
    {
        this.baseDamage = _meleeDamage;
        this.meleeDmg = _meleeDamage;
        return this;
    }

    public PreparedRangedWeapon withMeleeDamage(double _meleeDamage)
    {
        this.baseDamage = (float) _meleeDamage;
        this.meleeDmg = (float) _meleeDamage;
        return this;
    }

    public PreparedRangedWeapon withRangedDamage(float _rangedDamage)
    {
        this.rangedDamage = _rangedDamage;
        return this;
    }

    public PreparedRangedWeapon withRangedDamage(double _rangedDamage)
    {
        this.rangedDamage = (float) _rangedDamage;
        return this;
    }

    public PreparedRangedWeapon setBypassesArmor()
    {
        this.bypassesArmor = true;
        return this;
    }

    public PreparedRangedWeapon setBypassesArmor(boolean _bypassesArmor)
    {
        this.bypassesArmor = _bypassesArmor;
        return this;
    }

    public PreparedRangedWeapon withTexture(String _textureName)
    {
        this.textureName = _textureName;
        return this;
    }

    public PreparedRangedWeapon withName(String _name)
    {
        this.name = ModItems.idn+_name;
        return this;
    }

    public RangedWeapon register()
    {
        RangedWeapon ms = new RangedWeapon(name, meleeDmg, rangedDamage, soulConsumed, minSlots, maxSlots);
        ms.baseDamage=baseDamage;
        ms.bypassesArmor=bypassesArmor;
        ms.rarity=rarity;
        ms.texture = textureName;
        ms.durability=durability;
        ms.repairs =repairs;
        ms.repairItem=repairItem;
        ms.repairItemMeta = repairItemMeta;
        ModItems.ranged.add(ms);
        ModItems.registerItem(ms, ModItems.rangedtab);
        return ms;
    }

    public RangedWeapon compile()
    {
        RangedWeapon ms = new RangedWeapon(name, meleeDmg, rangedDamage, soulConsumed, minSlots, maxSlots);
        ms.baseDamage=baseDamage;
        ms.bypassesArmor=bypassesArmor;
        ms.rarity=rarity;
        ms.texture = textureName;
        ms.durability=durability;
        ms.repairs =repairs;
        ms.repairItem=repairItem;
        ms.repairItemMeta = repairItemMeta;
        return ms;
    }

    public static PreparedRangedWeapon newInstance()
    {
        return new PreparedRangedWeapon();
    }
}