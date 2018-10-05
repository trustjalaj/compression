import { NgModule } from '@angular/core';

import { JhipsterCompressionAppSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [JhipsterCompressionAppSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [JhipsterCompressionAppSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class JhipsterCompressionAppSharedCommonModule {}
