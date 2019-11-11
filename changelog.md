### v1.3.5
 - Fix MJ Consumer eating all the power, even with a full bridge. 

### v1.3.4
 - Fix energy duplication bug with IC2 when using multiple producers. The Producers now use an internal buffer that has
   the same size as the energy tier. I.e. Lv buffers 32 EU, EV buffers 2048 EU, etc

### v1.3.3
 - Fix recipes still requiring Buildcraft:Energy (now they need Buildcraft:Core)

### v1.3.2
 - Changed soft dependency from Buildcraft:Energy to Buildcraft:Core to allow modpacks to not include Buildcraft:Energy [#49](https://github.com/Xalcon/EnergyConverters/issues/49)

### v1.3.1
 - Add russian translations (by kellixon)

### v1.3.0
 - Feature freeze, Last 1.12.2 release (other than bugfixes)
 - Add some tooltips to FE and RF blocks to hopefully reduce my support workload

### v1.2.1
 - Fix crash with Railcraft (and potentially other mods) due to update code running on the logical client.

### v1.2.0
 - Add more configuration options for power conversion (tesla, rf, fe, mj)
 - Set buildcrafts conversion ratio to 15 instead of 10
 - Switch to config annotation system. This might break existing configurations. Please check your existing configs when upgrading!

### v1.1.0
 - Re-enabled buildcraft support
 - fix the MJ producer to not send more energy than a kinesis pipe is requesting
